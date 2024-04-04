import router from "../sparouter/router.js";
import handlers from "../sparouter/handlers.js";

  describe('router', function () {

      it('should find getStudents', function () {

           router.addRouteHandler("home", handlers.getHome)
           router.addRouteHandler("students", handlers.getStudents)
           router.addRouteHandler("students/10", handlers.getStudent)

           const handler = router.getRouteHandler("students")

           handler.name.should.be.equal("getStudents")
        })
  });
