package tagHandler;

import com.vladnickgo.Project.controller.dto.UserDto;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MyTagHandler extends TagSupport {
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession session = pageContext.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        try {
            if (user != null) {
                out.print(user.getRole() + ": " + user.getFirstName() + " " + user.getLastName() + "<br>" + user.getEmail());
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}
