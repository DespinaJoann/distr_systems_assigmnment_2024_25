package gr.dit.voluntia.demo.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Setter
@AllArgsConstructor
public class ResponseMsg {

    private String task;
    private Boolean success;


    public void responseMsgConsole() {
        if (this.success) {
            // TODO: add ansi colors to make it more fun
            System.out.println("Successfully executed the task: " + this.task);
        }
        System.out.println("Execution completed with error: " + this.task);
    }

    public ResponseEntity<String> responseMsgWeb() {
        if (this.success) {
            return new ResponseEntity<>("Task: "+ this.task + " successfully executed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task: "+ this.task + " failed", HttpStatus.BAD_REQUEST);
        }
    }


}



