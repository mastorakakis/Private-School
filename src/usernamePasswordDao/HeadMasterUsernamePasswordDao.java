package usernamePasswordDao;

import java.util.List;
import usernamePassword.UsernamePassword;

public class HeadMasterUsernamePasswordDao extends UsernamePasswordDao {

    public List<UsernamePassword> readHeadUsernamePasswordList() {
        String query = "SELECT h_id, username, password, role "
                + "     FROM headmaster_password "
                + "         INNER JOIN roles ON role_id = id;";
        return readUsernamePasswordList(query);
    }
}
