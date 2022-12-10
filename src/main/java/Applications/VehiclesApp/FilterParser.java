package Applications.VehiclesApp;

import Content.Vehicles.Car;
import Content.Vehicles.Scooter;
import Content.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;

/**
 * The type Filter parser.
 */
public class FilterParser {

    private final String prompt;
    private final ArrayList<Vehicle> toFilter;

    /**
     * Instantiates a new Filter parser.
     *
     * @param prompt   the prompt
     * @param toFilter the to filter
     */
    protected FilterParser(String prompt, ArrayList<Vehicle> toFilter) {
        this.prompt = prompt;
        this.toFilter = toFilter;
    }

    /**
     * Filter array list.
     *
     * @return the array list
     */
    protected ArrayList<Vehicle> filter() {

        if (this.prompt.isEmpty()) {
            return this.toFilter;
        }

        ArrayList<Vehicle> result = new ArrayList<>();
        Clause clause = readClause(this.prompt);
        for (Vehicle vehicle : this.toFilter) {
            if (verifyClause(vehicle, clause)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    private Boolean verifyClause(Vehicle vehicle, Clause clause) {
        if (clause.type.equals("OR")) {
            for (LogicalObject logicalObject : clause.logicalObjects) {
                if (logicalObject instanceof Literal) {
                    if (verifyLiteral(vehicle, (Literal) logicalObject)) {
                        return true;
                    }
                } else {
                    if (verifyClause(vehicle, (Clause) logicalObject)) {
                        return true;
                    }
                }
            }
            return false;
        }

        else { // AND
            for (LogicalObject logicalObject : clause.logicalObjects) {
                if (logicalObject instanceof Literal) {
                    if (!verifyLiteral(vehicle, (Literal) logicalObject)) {
                        return false;
                    }
                } else {
                    if (!verifyClause(vehicle, (Clause) logicalObject)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private Boolean verifyLiteral(Vehicle vehicle, Literal literal) {
        String[] split = literal.expr.split("=");
        String desiredValue = split[1];
        switch (split[0]) {
            case "Brand":
                return vehicle.getBrand().equals(desiredValue);
            case "Model":
                return vehicle.getModel().equals(desiredValue);
            case "Licence_Plate":
                return vehicle.getLicencePlate().equals(desiredValue);
            case "Value":
                return String.valueOf(vehicle.getValue()).equals(desiredValue);
            case "Type":
                if (desiredValue.equals("Scooter")) return vehicle instanceof Scooter;
                else return vehicle instanceof Car;
            default:
                return false;
        }
    }

    /**
     * Find if the clause in the string is an AND or OR clause
     * @param content the string
     * @return the type of the string
     */
    private String findType(String content) {
        String pure = content.replaceAll("\\(.*\\)", "");
        if (pure.contains(" and ")) {
            return "AND";
        }
        return "OR";
    }

    /**
     * Transform a String into a Clause logical object
     * @param content the string
     * @return A Clause logical object
     */
    private Clause readClause(String content) {
        String[] split = content.split(" ");
        Clause result = new Clause(new ArrayList<>(), this.findType(content));
        for (int i = 0; i < split.length; i += 2) {
            String current_expr = split[i];
            if (current_expr.contains("(")) {
                Clause internClause = new Clause(new ArrayList<>(), split[++i].toUpperCase(Locale.ROOT));
                internClause.add(new Literal(current_expr.replaceAll("\\(", "")));
                for (i++; i < split.length; i += 2) {
                    current_expr = split[i];
                    if (current_expr.contains(")")) {
                        internClause.add(new Literal(current_expr.replaceAll("\\)", "")));
                        result.add(internClause);
                        break;
                    } else {
                        internClause.add(new Literal(current_expr));
                    }
                }
            } else {
                result.add(new Literal(current_expr));
            }
        }
        return result;
    }

    /// Logical operator to parse

    private class LogicalObject {}

    private class Literal extends LogicalObject {
        private String expr;

        /**
         * Instantiates a new Literal.
         *
         * @param expr the expr
         */
        public Literal(String expr) {
            this.expr = expr;
        }

        @Override
        public String toString() {
            return this.expr;
        }
    }

    private class Clause extends LogicalObject {
        private final ArrayList<LogicalObject> logicalObjects;
        private final String type;    // AND or OR

        /**
         * Instantiates a new Clause.
         *
         * @param logicalObjects the logical objects
         * @param type           the type
         */
        public Clause(ArrayList<LogicalObject> logicalObjects, String type) {
            this.logicalObjects = logicalObjects;
            this.type = type;
        }

        /**
         * Add.
         *
         * @param logicalObject the logical object
         */
        public void add(LogicalObject logicalObject) {
            this.logicalObjects.add(logicalObject);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.logicalObjects.size(); i++) {
                LogicalObject logicalObject = this.logicalObjects.get(i);
                if (i < this.logicalObjects.size() - 1) {
                    stringBuilder.append(logicalObject + " " + type + " ");
                } else {
                    stringBuilder.append(logicalObject);
                }
            }
            return "(" + stringBuilder.toString() + ")";
        }
    }
}

