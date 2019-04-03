package usernamePasswordDao;

import java.util.List;
import java.util.Scanner;
import usernamePassword.UsernamePassword;

public class TrainerUsernamePasswordDao extends UsernamePasswordDao {

    public List<UsernamePassword> readTrainerUsernamePasswordList() {
        String query = "SELECT t_id, username, password, role "
                + "     FROM trainer_password "
                + "         INNER JOIN roles ON role_id = id;";
        return readUsernamePasswordList(query);
    }

    public UsernamePassword readByTrainerUsernamePassword(int id) {
        String query = "SELECT t_id, username, password, role "
                + "     FROM trainer_password "
                + "         INNER JOIN roles ON role_id = id;";
        return readByUsernamePasswordId(id, query);
    }

    public UsernamePassword createNewTrainerUsernamePassword(Scanner sc) {
        String query = "INSERT INTO trainer_password (st_id, "
                + "                                   username, "
                + "                                   password, "
                + "                                   role_id)"
                + "     VALUES (?, ?, ?, 2);";
        return createNewUsernamePassword(sc, query);
    }

    public UsernamePassword updateByTrainerUsernamePasswordId(int id,
            Scanner sc) {
        String query = "UPDATE trainer_password "
                + "     SET username = ?, "
                + "         password = ?, role_id = 2 "
                + "     WHERE st_id = ?;";
        return updateByUsernamePasswordId(id, sc, query);
    }
}
