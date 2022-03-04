package br.com.monitoratec.farol.graphql.resolvers.mutation.user;

import br.com.monitoratec.farol.auth.model.AccessingEntity;
import br.com.monitoratec.farol.graphql.exceptions.ErrorOnProcessingRequestException;
import br.com.monitoratec.farol.graphql.model.input.login.PasswordUpdateInput;
import br.com.monitoratec.farol.graphql.model.responses.user.CommonResponseWithClientInformation;
import br.com.monitoratec.farol.graphql.resolvers.base.BaseResolver;
import br.com.monitoratec.farol.graphql.utils.TimedOutHandledPromiser;
import br.com.monitoratec.farol.sql.model.user.Client;
import br.com.monitoratec.farol.sql.repository.user.ClientRepository;
import br.com.monitoratec.farol.utils.password.PasswordUtils;
import br.com.monitoratec.farol.utils.responses.StatusCodes;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class UpdateClientPasswordImpl extends BaseResolver implements GraphQLMutationResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateClientPasswordImpl.class);

    private final ClientRepository clientRepository;
    private final PasswordUtils passwordUtils;

    public UpdateClientPasswordImpl(ClientRepository clientRepository, PasswordUtils passwordUtils) {
        this.clientRepository = clientRepository;
        this.passwordUtils = passwordUtils;
    }

    public CompletableFuture<CommonResponseWithClientInformation> updateClientPassword(
            PasswordUpdateInput passwords,
            DataFetchingEnvironment dataFetchingEnvironment
    ) {
        super.logRequest(LOGGER, this);

        CompletableFuture<CommonResponseWithClientInformation> responsePromise = TimedOutHandledPromiser.genPromise();

        super.validateUserPromise(dataFetchingEnvironment).bindToResponsePromise(responsePromise).anyOf(Arrays.asList(AccessingEntity.values()))
                .thenAccept(cachedTrustedToken -> {
                    // Validated access
                    Optional<Client> clientOptional = clientRepository.findByIdAndActiveTrue(cachedTrustedToken.userCachedInfo.entityID);
                    if (clientOptional.isEmpty()) {
                        throw new ErrorOnProcessingRequestException(StatusCodes.Error.User.USER_NOT_FOUND);
                    }

                    Client client = clientOptional.get();

                    if (passwords != null) {
                        if (!passwordUtils.doesPasswordMatch(client.getPasswordHash(), passwords.getOldPassword())) {
                            throw new ErrorOnProcessingRequestException(StatusCodes.Error.User.PASSWORD_DONT_MATCH);
                        }
                        client.setPasswordHash(passwordUtils.hashPassword(passwords.getPassword()));
                        client.setTemporaryPassword(false);
                    }

                    client = clientRepository.save(client);

                    CommonResponseWithClientInformation commonResponseWithClientInformation = new CommonResponseWithClientInformation(StatusCodes.Success.User.GOT_USER, client);
                    responsePromise.complete(commonResponseWithClientInformation);
                });
        return responsePromise;
    }

}
