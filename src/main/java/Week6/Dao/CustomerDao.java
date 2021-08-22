package Week6.Dao;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDao {
    private Integer Id;
    private String Name;
    private Integer identityType;
    private String identityNum;
    private Integer phoneNum;
    private String email;
    private Character gender;
    private Date createdTime;
    private Date modifiedTime;
}
