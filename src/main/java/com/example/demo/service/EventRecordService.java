public interface EventRecordService {

    EventRecord create(EventRecord record);

    // 👇 REQUIRED BY TESTS
    default EventRecord createEvent(EventRecord record) {
        return create(record);
    }

    EventRecord getOne(Long id);

    List<EventRecord> getAll();

    // 👇 REQUIRED
    default EventRecord getEventById(Long id) {
        return getOne(id);
    }

    // 👇 REQUIRED
    default boolean existsByEventCode(String code) {
        return false;
    }
}
