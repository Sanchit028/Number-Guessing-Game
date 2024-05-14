import java.io.IOException;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GuessServlet")
public class GuessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("again".equals(action)) {
            session.removeAttribute("randomNumber");
            session.removeAttribute("attempts");
            session.removeAttribute("score");
            request.getRequestDispatcher("index2.jsp").forward(request, response);
        } else if ("guess".equals(action)) {
            int randomNumber, diff=10;
            int guess = Integer.parseInt(request.getParameter("guess"));
            int attempts = Integer.parseInt(request.getParameter("attempts"));
            int highscore = Integer.parseInt(request.getParameter("highscore"));
            String difficulty = request.getParameter("difficulty");
            
            if (session.getAttribute("highscore") == null) {
                session.setAttribute("highscore", 0);
            }
            
            if (session.getAttribute("randomNumber") == null) {
                randomNumber = new Random().nextInt(100) + 1;
                session.setAttribute("randomNumber", randomNumber);
                session.setAttribute("attempts", 0);
                session.setAttribute("score", 0);
                session.setAttribute("difficulty", difficulty);
            } else {
                randomNumber = (int) session.getAttribute("randomNumber");
                attempts = (int) session.getAttribute("attempts");
                highscore = (int) session.getAttribute("highscore");
            }

            attempts++;
            if(session.getAttribute("difficulty").equals("easy")) {
            	diff=10;
            }else if(session.getAttribute("difficulty").equals("intr")) {
            	diff=7;
            }else if(session.getAttribute("difficulty").equals("hard")) {
            	diff=4;
            }else if(session.getAttribute("difficulty").equals("imp")) {
            	diff=1;
            }
            
            
            if (guess == randomNumber) {
            	int score=diff-attempts+1;
                request.setAttribute("message", "Congratulations! You guessed the number.");
                session.setAttribute("score", score);
                if(score>highscore) {
                	session.setAttribute("highscore", score);
                }
                session.removeAttribute("randomNumber");
                session.removeAttribute("attempts");
                request.getRequestDispatcher("end.jsp").forward(request, response);
            } else if (attempts >= diff) {
                request.setAttribute("message", "Sorry, you've used all your attempts.");
                session.removeAttribute("randomNumber");
                session.removeAttribute("attempts");
                request.getRequestDispatcher("end.jsp").forward(request, response);
            } else if (guess < randomNumber) {
                request.setAttribute("message", "Too low. Try again.");
            } else {
                request.setAttribute("message", "Too high. Try again.");
            }

            session.setAttribute("attempts", attempts);
        }

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

}
