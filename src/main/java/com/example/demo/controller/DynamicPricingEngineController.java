@RestController
@RequestMapping("/api/pricing")
public class DynamicPricingEngineController {

    private final DynamicPricingEngineService service;

    public DynamicPricingEngineController(DynamicPricingEngineService service) {
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
