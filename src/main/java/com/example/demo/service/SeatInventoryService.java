public interface SeatInventoryService {

    SeatInventoryRecord save(SeatInventoryRecord record);

    List<SeatInventoryRecord> findAll();

    SeatInventoryRecord findByEventId(Long eventId);

    // 👇 REQUIRED BY TESTS
    default SeatInventoryRecord createInventory(SeatInventoryRecord record) {
        return save(record);
    }
}
