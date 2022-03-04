package br.com.monitoratec.farol.graphql.resolvers.mutation.campaign;

import br.com.monitoratec.farol.auth.model.AccessingEntity;
import br.com.monitoratec.farol.graphql.model.dtos.campaign.CampaignDTO;
import br.com.monitoratec.farol.graphql.model.input.campaign.CampaignInput;
import br.com.monitoratec.farol.graphql.model.responses.campaign.CommonResponseWithCampaign;
import br.com.monitoratec.farol.graphql.resolvers.base.BaseResolver;
import br.com.monitoratec.farol.graphql.utils.TimedOutHandledPromiser;
import br.com.monitoratec.farol.service.campaign.CampaignService;
import br.com.monitoratec.farol.utils.responses.StatusCodes;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Component
public class UpdateCampaignImpl extends BaseResolver implements GraphQLMutationResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCampaignImpl.class);

    private final CampaignService service;

    public UpdateCampaignImpl(CampaignService service) {
        this.service = service;
    }

    public CompletableFuture<CommonResponseWithCampaign> updateCampaign(Long id, CampaignInput input, DataFetchingEnvironment dataFetchingEnvironment) {
        super.logRequest(LOGGER, this);

        CompletableFuture<CommonResponseWithCampaign> responsePromise = TimedOutHandledPromiser.genPromise();

        super.validateUserPromise(dataFetchingEnvironment).bindToResponsePromise(responsePromise).anyOf(Arrays.asList(AccessingEntity.values()))
                .thenAccept(cachedTrustedToken -> {
                    final CampaignDTO dto = service.update(id, input);

                    final CommonResponseWithCampaign response = new CommonResponseWithCampaign(StatusCodes.Success.Campaign.UPDATED_CAMPAIGN, dto);
                    responsePromise.complete(response);
                });
        return responsePromise;
    }
}
