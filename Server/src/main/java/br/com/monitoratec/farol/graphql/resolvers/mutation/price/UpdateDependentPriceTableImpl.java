package br.com.monitoratec.farol.graphql.resolvers.mutation.price;

import br.com.monitoratec.farol.auth.model.AccessingEntity;
import br.com.monitoratec.farol.graphql.model.dtos.price.DependentPriceTableDTO;
import br.com.monitoratec.farol.graphql.model.input.price.DependentPriceTableInput;
import br.com.monitoratec.farol.graphql.model.responses.price.CommonResponseWithDependentPriceTable;
import br.com.monitoratec.farol.graphql.resolvers.base.BaseResolver;
import br.com.monitoratec.farol.graphql.utils.TimedOutHandledPromiser;
import br.com.monitoratec.farol.service.price.DependentPriceTableService;
import br.com.monitoratec.farol.utils.responses.StatusCodes;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Component
public class UpdateDependentPriceTableImpl extends BaseResolver implements GraphQLMutationResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDependentPriceTableImpl.class);

    private final DependentPriceTableService service;

    public UpdateDependentPriceTableImpl(DependentPriceTableService service) {
        this.service = service;
    }

    public CompletableFuture<CommonResponseWithDependentPriceTable> updateDependentPriceTable(Long id, DependentPriceTableInput input, DataFetchingEnvironment dataFetchingEnvironment) {
        super.logRequest(LOGGER, this);

        CompletableFuture<CommonResponseWithDependentPriceTable> responsePromise = TimedOutHandledPromiser.genPromise();

        super.validateUserPromise(dataFetchingEnvironment).bindToResponsePromise(responsePromise).anyOf(Arrays.asList(AccessingEntity.values()))
                .thenAccept(cachedTrustedToken -> {
                    final DependentPriceTableDTO dto = service.update(id, input);

                    final CommonResponseWithDependentPriceTable response = new CommonResponseWithDependentPriceTable(StatusCodes.Success.PriceTable.UPDATED_PRICE_TABLE, dto);
                    responsePromise.complete(response);
                });
        return responsePromise;
    }
}
