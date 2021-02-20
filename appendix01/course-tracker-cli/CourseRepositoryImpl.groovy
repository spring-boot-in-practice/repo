@Repository
class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    Iterable<Course> findAll() {
         jdbcTemplate.query("""SELECT COURSE_ID, COURSE_NAME, COURSE_CATEGORY, COURSE_RATING, COURSE_DESCRIPTION FROM COURSES""", {
            resultSet, newRow -> new Course(
                id : resultSet.getLong(1),
                name : resultSet.getString(2),
                category : resultSet.getString(3),
                rating : resultSet.getInt(4),
                description : resultSet.getString(5))
         } as RowMapper)
    }

    Iterable<Course> findAllByCourseCategory(String category) {
         jdbcTemplate.query("""SELECT COURSE_ID, COURSE_NAME, COURSE_CATEGORY, COURSE_RATING, COURSE_DESCRIPTION FROM COURSES WHERE COURSE_CATEGORY=?""", {
            resultSet, newRow -> new Course(
                id : resultSet.getLong(1),
                name : resultSet.getString(2),
                category : resultSet.getString(3),
                rating : resultSet.getInt(4),
                description : resultSet.getString(5))
         } as RowMapper, category)
    }
}