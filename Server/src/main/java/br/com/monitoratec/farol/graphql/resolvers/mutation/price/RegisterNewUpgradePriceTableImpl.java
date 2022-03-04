package br.com.monitoratec.farol.graphql.resolvers.mutation.price;

import br.com.monitoratec.farol.auth.model.AccessingEntity;
import br.com.monitoratec.farol.graphql.model.dtos.price.UpgradePriceTableDTO;
import br.com.monitoratec.farol.graphql.model.input.price.UpgradePriceTableInput;
import br.com.monitoratec.farol.graphql.model.responses.price.CommonResponseWithUpgradePriceTable;
import br.com.monitoratec.farol.graphql.resolvers.base.BaseResolver;
import br.com.monitoratec.farol.graphql.utils.TimedOutHandledPromiser;
import br.com.monitoratec.farol.service.price.UpgradePriceTableService;
import br.com.monitoratec.farol.utils.responses.StatusCodes;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Component
public class RegisterNewUpgradePriceTableImpl extends BaseResolver implements GraphQLMutationResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterNewUpgradePriceTableImpl.class);

    private final UpgradePriceTableService service;

    public RegisterNewUpgradePriceTableImpl(UpgradePriceTableService service) {
        this.service = service;
    }

    public CompletableFuture<CommonResponseWithUpgradePriceTable> registerNewUpgradePriceTable(UpgradePriceTableInput input, DataFetchingEnvironment dataFetchingEnvironment) {
        super.logRequest(LOGGER, this);

        CompletableFuture<CommonResponseWithUpgradePriceTable> responsePromise = TimedOutHandledPromiser.genPromise();

        super.validateUserPromise(dataFetchingEnvironment).bindToResponsePromise(responsePromise).anyOf(Arrays.asList(AccessingEntity.values()))
                .thenAccept(cachedTrustedToken -> {
                    final UpgradePriceTableDTO dto = service.create(input);

                    final CommonResponseWithUpgradePriceTable response = new CommonResponseWithUpgradePriceTable(StatusCodes.Success.PriceTable.REGISTERED_NEW_PRICE_TABLE, dto);
                    responsePromise.complete(response);
                });
        return responsePromise;
    }
}
