import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        double annualInterestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));

        // create loan object
        Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

        // compute monthly and total loan payments
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // prepare response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // display payments and loan date
        out.println("<html>");
        out.println("<head><title>Loan Payment Results</title></head>");
        out.println("<body>");
        out.println("<h2>Loan Payment Results</h2>");
        out.println("<p>Loan Amount: $" + loan.getLoanAmount() + "</p>");
        out.println("<p>Annual Interest Rate: " + loan.getAnnualInterestRate() + "%</p>");
        out.println("<p>Number of Years: " + loan.getNumberOfYears() + "</p>");
        out.println("<p>Loan Date: " + loan.getLoanDate() + "</p>");
        out.println("<p>Monthly Payment: $" + monthlyPayment + "</p>");
        out.println("<p>Total Payment: $" + totalPayment + "</p>");
        out.println("</body></html>");
    }
}
