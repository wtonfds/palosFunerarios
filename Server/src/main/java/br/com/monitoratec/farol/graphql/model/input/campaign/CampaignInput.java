package br.com.monitoratec.farol.graphql.model.input.campaign;

import br.com.monitoratec.farol.graphql.customTypes.Date;
import br.com.monitoratec.farol.graphql.customTypes.Time;
import br.com.monitoratec.farol.graphql.model.dtos.campaign.DeliveryModeDTO;
import br.com.monitoratec.farol.graphql.model.dtos.campaign.RecurrenceTypeDTO;

import java.util.Objects;

public class CampaignInput {
    private String name;
    private Date startDate;
    private Date endDate;
    private RecurrenceTypeDTO recurrenceType;
    private int recurrence;
    private Time timeToSend;
    private String message;
    private DeliveryModeDTO deliveryMode;
    private boolean inactiveClients;
    private boolean birthdayClients;
    private boolean childrenWithAge;
    private boolean expiringContracts;
    private boolean withoutCoupons;
    private boolean withoutTem;
    private boolean withoutFuneralAssistance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public RecurrenceTypeDTO getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(RecurrenceTypeDTO recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public Time getTimeToSend() {
        return timeToSend;
    }

    public void setTimeToSend(Time timeToSend) {
        this.timeToSend = timeToSend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeliveryModeDTO getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryModeDTO deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public boolean isInactiveClients() {
        return inactiveClients;
    }

    public void setInactiveClients(boolean inactiveClients) {
        this.inactiveClients = inactiveClients;
    }

    public boolean isBirthdayClients() {
        return birthdayClients;
    }

    public void setBirthdayClients(boolean birthdayClients) {
        this.birthdayClients = birthdayClients;
    }

    public boolean isChildrenWithAge() {
        return childrenWithAge;
    }

    public void setChildrenWithAge(boolean childrenWithAge) {
        this.childrenWithAge = childrenWithAge;
    }

    public boolean isExpiringContracts() {
        return expiringContracts;
    }

    public void setExpiringContracts(boolean expiringContracts) {
        this.expiringContracts = expiringContracts;
    }

    public boolean isWithoutCoupons() {
        return withoutCoupons;
    }

    public void setWithoutCoupons(boolean withoutCoupons) {
        this.withoutCoupons = withoutCoupons;
    }

    public boolean isWithoutTem() {
        return withoutTem;
    }

    public void setWithoutTem(boolean withoutTem) {
        this.withoutTem = withoutTem;
    }

    public boolean isWithoutFuneralAssistance() {
        return withoutFuneralAssistance;
    }

    public void setWithoutFuneralAssistance(boolean withoutFuneralAssistance) {
        this.withoutFuneralAssistance = withoutFuneralAssistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignInput input = (CampaignInput) o;
        return recurrence == input.recurrence &&
                inactiveClients == input.inactiveClients &&
                birthdayClients == input.birthdayClients &&
                childrenWithAge == input.childrenWithAge &&
                expiringContracts == input.expiringContracts &&
                withoutCoupons == input.withoutCoupons &&
                withoutTem == input.withoutTem &&
                withoutFuneralAssistance == input.withoutFuneralAssistance &&
                Objects.equals(name, input.name) &&
                Objects.equals(startDate, input.startDate) &&
                Objects.equals(endDate, input.endDate) &&
                recurrenceType == input.recurrenceType &&
                Objects.equals(timeToSend, input.timeToSend) &&
                Objects.equals(message, input.message) &&
                deliveryMode == input.deliveryMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate, recurrenceType, recurrence, timeToSend, message, deliveryMode,
                inactiveClients, birthdayClients, childrenWithAge, expiringContracts, withoutCoupons, withoutTem, withoutFuneralAssistance);
    }

    @Override
    public String toString() {
        return "CampaignInput{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", recurrenceType=" + recurrenceType +
                ", recurrence=" + recurrence +
                ", timeToSend=" + timeToSend +
                ", message='" + message + '\'' +
                ", deliveryMode=" + deliveryMode +
                ", inactiveClients=" + inactiveClients +
                ", birthdayClients=" + birthdayClients +
                ", childrenWithAge=" + childrenWithAge +
                ", expiringContracts=" + expiringContracts +
                ", withoutCoupons=" + withoutCoupons +
                ", withoutTem=" + withoutTem +
                ", withoutFuneralAssistance=" + withoutFuneralAssistance +
                '}';
    }
}
