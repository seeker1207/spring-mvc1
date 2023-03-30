package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServeletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontControllerServeletV2() {
        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServeletV2.service");

        String requestURI = req.getRequestURI();

        ControllerV2 controllerV2 = controllerV1Map.get(requestURI);
        if (controllerV2 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controllerV2.process(req, resp);
        view.render(req, resp);
    }
}
