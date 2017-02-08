package sample;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Antoine Janvier
 * on 08/02/17.
 */

public class Connexion_JDBC {

    private List<String> messages = new ArrayList<String>();

    public List<String> compute_tests(HttpServletRequest request ) {

        return messages;
    }
}
