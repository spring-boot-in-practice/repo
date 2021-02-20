interface CourseRepository {
    Iterable<Course> findAll()
    Iterable<Course> findAllByCourseCategory(String category)
}