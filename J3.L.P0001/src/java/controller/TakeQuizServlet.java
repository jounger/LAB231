/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import dao.impl.AnswerDAOImpl;
import dao.impl.AskDAOImpl;
import dao.impl.OptionDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.QuizDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answer;
import model.Ask;
import model.Option;
import model.Question;
import model.Quiz;
import model.User;
import utils.SecurityStore;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "TakeQuizServlet", urlPatterns = {"/take-quiz"})
public class TakeQuizServlet extends HttpServlet {

    private final QuizDAOImpl quizDAOImpl = new QuizDAOImpl();
    private final QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();
    private final AskDAOImpl askDAOImpl = new AskDAOImpl();
    private final OptionDAOImpl optionDAOImpl = new OptionDAOImpl();
    private final AnswerDAOImpl answerDAOImpl = new AnswerDAOImpl();

    private int countCorrectOptions(List<Option> options) {
        int count = 0;
        for (Option opt : options) {
            if (opt.isCorrect()) {
                count++;
            }
        }
        return count;
    }

    private int countCorrectAnswers(List<Answer> answers) {
        int count = 0;
        for (Answer ans : answers) {
            if (ans.getOption().isCorrect()) {
                count++;
            }
        }
        return count;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizId = request.getParameter("quiz_id");
        Quiz currentQuiz;
        if (Tool.isNull(quizId)) {
            currentQuiz = quizDAOImpl.findById(Integer.parseInt(quizId));
            if (currentQuiz.getDateStop().after(new Date())) {
                int score = 0;
                for (Ask ask : currentQuiz.getAsks()) {
                    int countAns = countCorrectAnswers(ask.getAnswers());
                    int countOpt = countCorrectOptions(ask.getQuestion().getOptions());
                    if (countAns == countOpt) {
                        score++;
                    }
                }
                double finalScore = score / currentQuiz.getQuantity();
                currentQuiz.setScore(finalScore);
                request.setAttribute("quiz", currentQuiz);
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/result-quiz.jsp").forward(request, response);
                return;
            }
        } else {
            currentQuiz = quizDAOImpl.findCurrentQuiz();
        }
        if (currentQuiz != null) {
            for (Ask ask : currentQuiz.getAsks()) {
                // FIND an ask haven't answer yet
                if (ask.getAnswers().isEmpty()) {
                    request.setAttribute("question", ask.getQuestion());
                    break;
                }
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/quiz.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/take-quiz.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizId = request.getParameter("quiz_id");
        Quiz currentQuiz;
        if (Tool.isNull(quizId)) {
            currentQuiz = quizDAOImpl.findById(Integer.parseInt(quizId));
            if (currentQuiz.getDateStop().after(new Date())) {
                response.sendRedirect(this.getServletContext().getContextPath() + "/take-quiz?quiz_id=" + currentQuiz.getId());
            }
        } else {
            currentQuiz = quizDAOImpl.findCurrentQuiz();
        }
        if (currentQuiz != null) {
//            for (Ask ask : currentQuiz.getAsks()) {
            for (int i = 0; i < currentQuiz.getAsks().size(); i++) {
                Ask ask = currentQuiz.getAsks().get(i);
                // FIND an ask haven't answer yet
                if (ask.getAnswers().isEmpty()) {
                    String[] answers = request.getParameterValues("answers");
                    for (String ans : answers) {
                        Option option = optionDAOImpl.findById(Integer.parseInt(ans));
                        if (option != null) {
                            Answer answer = new Answer();
                            answer.setOption(option);
                            answerDAOImpl.saveInAsk(answer, ask.getId());
                            askDAOImpl.update(ask); // UPDATE Answered time
                        }
                    }
                    if (i == currentQuiz.getAsks().size() - 1) {
                        response.sendRedirect(this.getServletContext().getContextPath() + "/take-quiz?quiz_id=" + currentQuiz.getId());
                    }
                    break;
                }
            }
        } else {
            String quantity = request.getParameter("quantity");
            if (Tool.isNull(quantity) || Tool.toInteger(quantity, 0) < 1) {
                request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "Number of questions must be number and greater than 0");
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/take-quiz.jsp").forward(request, response);
            } else {
                int qty = Integer.parseInt(quantity);
                // GET (QTY) RANDOM QUESTIONS
                List<Question> questions = questionDAOImpl.findByRandom(1, qty);
                User user = SecurityStore.getAuth(request.getSession());
                List<Ask> asks = new ArrayList<>();

                for (Question question : questions) {
                    Ask ask = new Ask();
                    ask.setQuestion(question);
                    asks.add(ask);
                }
                Quiz quiz = new Quiz();
                quiz.setQuantity(qty);
                quiz.setUser(user);
                quiz.setAsks(asks);
                int savedQuizId = quizDAOImpl.save(quiz);
                if (savedQuizId != -1) {
                    response.sendRedirect(this.getServletContext().getContextPath() + "/take-quiz?quiz_id=" + savedQuizId);
                }
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/quiz.jsp").forward(request, response);
            }
        }
    }
}
