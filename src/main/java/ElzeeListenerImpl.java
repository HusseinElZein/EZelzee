import org.antlr.v4.runtime.*;
import java.util.HashMap;
import java.util.Map;

public class ElzeeListenerImpl extends ElzeeBaseListener {
    private Map<String, String> symbolTable = new HashMap<>();

    @Override
    public void enterAssigningExpr(ElzeeParser.AssigningExprContext ctx) {
        String type = ctx.type().getText();
        String variableName = ctx.ID().getText();
        String value = ctx.content().getText();

        // Check if the variable already exists
        if (symbolTable.containsKey(variableName)) {
            System.err.println("Error: Variable already exists.");
            System.exit(0);
            return;
        }

        // Check if the variable type matches the assigned value type
        if (type.equals("tallet") && value.matches("[0-9]+")) {
            symbolTable.put(variableName, value);
        } else if (type.equals("ordet") && value.startsWith("\"") && value.endsWith("\"")) {
            symbolTable.put(variableName, value);
        } else {
            System.err.println("Error: Illegal assignment! Variable type and value type do not match.");
            System.exit(0);
        }
    }

    @Override
    public void enterReassigningExpr(ElzeeParser.ReassigningExprContext ctx) {
        System.out.println("reassigning");
        String variableName = ctx.ID(0).getText();
        String newValue = ctx.ID(1).getText();

        // Update the value in the symbol table
        if (symbolTable.containsKey(newValue)) {
            symbolTable.put(variableName, symbolTable.get(newValue));
        } else {
            System.err.println("Error: Variable " + newValue + " not found!");
            System.exit(0);
        }
    }

    @Override
    public void enterAdditionExpr(ElzeeParser.AdditionExprContext ctx) {
        String variableName = ctx.ID(0).getText();

        String operand;
        if(ctx.ID().size() > 1){
            operand = ctx.ID(1).getText();
        }
        else{
            operand = ctx.NUMBER().getText();
        }

        // Check if the operand is a variable
        if (symbolTable.containsKey(operand)) {
            operand = symbolTable.get(operand); // If it's a variable, get its value from the symbol table
        }

        // Check if both the variable and the operand are numeric literals
        if (symbolTable.containsKey(variableName) && symbolTable.get(variableName).matches("[0-9]+") &&
                operand.matches("[0-9]+")) {
            int result = Integer.parseInt(symbolTable.get(variableName)) + Integer.parseInt(operand);
            symbolTable.put(variableName, String.valueOf(result));
        } else {
            System.err.println("Error: Cannot perform addition. Both operands must be numeric literals or variables containing numeric literals.");
            System.exit(0);
        }
    }


    @Override
    public void enterPrintExpr(ElzeeParser.PrintExprContext ctx) {
        String value = ctx.value().getText(); // Use ctx.getText() to get the raw text of the print expression

        // Check if the value is a variable
        if (symbolTable.containsKey(value)) {
            value = symbolTable.get(value); // If it's a variable, get its value from the symbol table
        }

        // Check if the value is a numeric literal
        if (value.matches("[0-9]+")) {
            System.out.println(value);
        } else if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
            // If the value is a string literal, remove the double quotes and print the content
            System.out.println(value.substring(1, value.length() - 1));
        } else {
            System.err.println("Error: Variable, string, or number " + value + " not found!");
            System.exit(0);
        }
    }
    @Override public void enterSubtractionExpr(ElzeeParser.SubtractionExprContext ctx) {
        String variableName = ctx.ID(0).getText();

        String operand;
        if(ctx.ID().size() > 1){
            operand = ctx.ID(1).getText();
        }
        else{
            operand = ctx.NUMBER().getText();
        }

        // Check if the operand is a variable
        if (symbolTable.containsKey(operand)) {
            operand = symbolTable.get(operand); // If it's a variable, get its value from the symbol table
        }

        // Check if both the variable and the operand are numeric literals
        if (symbolTable.containsKey(variableName) && symbolTable.get(variableName).matches("[0-9]+") &&
                operand.matches("[0-9]+")) {
            int result = Integer.parseInt(symbolTable.get(variableName)) - Integer.parseInt(operand);
            symbolTable.put(variableName, String.valueOf(result));
        } else {
            System.err.println("Error: Cannot perform addition. Both operands must be numeric literals or variables containing numeric literals.");
            System.exit(0);
        }
    }

    @Override public void enterMultiplyExpr(ElzeeParser.MultiplyExprContext ctx) {
        String variableName = ctx.ID(0).getText();

        String operand;
        if(ctx.ID().size() > 1){
            operand = ctx.ID(1).getText();
        }
        else{
            operand = ctx.NUMBER().getText();
        }

        // Check if the operand is a variable
        if (symbolTable.containsKey(operand)) {
            operand = symbolTable.get(operand); // If it's a variable, get its value from the symbol table
        }

        // Check if both the variable and the operand are numeric literals
        if (symbolTable.containsKey(variableName) && symbolTable.get(variableName).matches("[0-9]+") &&
                operand.matches("[0-9]+")) {
            int result = Integer.parseInt(symbolTable.get(variableName)) * Integer.parseInt(operand);
            symbolTable.put(variableName, String.valueOf(result));
        } else {
            System.err.println("Error: Cannot perform addition. Both operands must be numeric literals or variables containing numeric literals.");
            System.exit(0);
        }

    }

    @Override public void enterDivideExpr(ElzeeParser.DivideExprContext ctx) {
        String variableName = ctx.ID(0).getText();

        String operand;
        if(ctx.ID().size() > 1){
            operand = ctx.ID(1).getText();
        }
        else{
            operand = ctx.NUMBER().getText();
        }

        // Check if the operand is a variable
        if (symbolTable.containsKey(operand)) {
            operand = symbolTable.get(operand); // If it's a variable, get its value from the symbol table
        }

        // Check if both the variable and the operand are numeric literals
        if (symbolTable.containsKey(variableName) && symbolTable.get(variableName).matches("[0-9]+") &&
                operand.matches("[0-9]+")) {
            int result = Integer.parseInt(symbolTable.get(variableName)) / Integer.parseInt(operand);
            symbolTable.put(variableName, String.valueOf(result));
        } else {
            System.err.println("Error: Cannot perform addition. Both operands must be numeric literals or variables containing numeric literals.");
            System.exit(0);
        }
    }
}
