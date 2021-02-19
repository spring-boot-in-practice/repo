@RestController    
class DemoRestController {   

    @GetMapping("/")  
    def hello() {
        "Welcome to Spring Boot CLI"  
    }
}
