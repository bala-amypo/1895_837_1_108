@RestController
@RequestMapping("/api/pricing")
public class DynamicPricingController {

    private final DynamicPricingEngineService service;

    public DynamicPricingController(DynamicPricingEngineService service) {
        this.service = service;
    }

    @PostMapping("/{eventCode}/compute")
    public DynamicPriceRecord compute(@PathVariable String eventCode) {
        return service.computePrice(eventCode);
    }

    @GetMapping("/{eventCode}")
    public List<DynamicPriceRecord> history(@PathVariable String eventCode) {
        return service.history(eventCode);
    }
}
