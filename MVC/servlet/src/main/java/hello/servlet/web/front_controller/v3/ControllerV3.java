package hello.servlet.web.front_controller.v3;

import hello.servlet.web.front_controller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
