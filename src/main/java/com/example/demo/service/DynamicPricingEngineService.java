public interface DynamicPricingEngineService {

    DynamicPriceRecord computePrice(Long eventId);

    List<DynamicPriceRecord> getAllComputedPrices();

    // 👇 REQUIRED BY TESTS
    default DynamicPriceRecord computeDynamicPrice(Long eventId) {
        return computePrice(eventId);
    }

    // 👇 REQUIRED
    default List<DynamicPriceRecord> getPriceHistory(Long eventId) {
        return getAllComputedPrices();
    }
}
