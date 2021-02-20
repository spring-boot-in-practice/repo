@Grab("h2")
@Grab("spring-boot-starter-thymeleaf")
@Controller
@RequestMapping
class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    def getAllCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        "courses";
    }

    @GetMapping("{category}")
    def getAllCourses(@PathVariable("category") String category, Model model) {
        model.addAttribute("courses", courseRepository.findAllByCourseCategory(category));
        "courses";
    }
}