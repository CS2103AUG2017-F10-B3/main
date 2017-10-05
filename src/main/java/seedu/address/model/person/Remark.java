package seedu.address.model.person;



public class Remark {

    public static final String MESSAGE_REMARK_CONSTRAINTS =
            "Person remarks can take any values, can even be blank";

         public final String value;

         public Remark(String remark) {
             this.value = remark;
        }
        @Override
        public String toString() {
               return value;
         }
        @Override
        public boolean equals(Object other) {
               return other == this // short circuit if same object
                                || (other instanceof Remark // instanceof handles nulls
                                && this.value.equals(((Remark) other).value)); // state check
        }
        @Override
        public int hashCode() {
                return value.hashCode();
        }
 }