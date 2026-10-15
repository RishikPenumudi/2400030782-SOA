package soa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tourists")
public class TouristController {
 private final JdbcTemplate jdbc;
 public TouristController(JdbcTemplate jdbc) { this.jdbc = jdbc; }
 private boolean validToken(String token) { return "valid123".equals(token); }

 @PostMapping
 public ResponseEntity<?> add(@RequestHeader(value="Token", required=false) String token, @RequestBody Tourist tourist) {
  if (!validToken(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Token Validation Failed"));
  jdbc.update("INSERT INTO tourists(name, location, country) VALUES (?, ?, ?)", tourist.getName(), tourist.getLocation(), tourist.getCountry());
  return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Tourist added successfully"));
 }
 @GetMapping
 public ResponseEntity<?> all(@RequestHeader(value="Token", required=false) String token) {
  if (!validToken(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Token Validation Failed"));
  List<Map<String,Object>> rows = jdbc.queryForList("SELECT id, name, location, country FROM tourists ORDER BY id");
  return ResponseEntity.ok(rows);
 }
 @PutMapping("/{id}")
 public ResponseEntity<?> update(@RequestHeader(value="Token", required=false) String token, @PathVariable long id, @RequestBody Tourist tourist) {
  if (!validToken(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Token Validation Failed"));
  int n = jdbc.update("UPDATE tourists SET name=?, location=?, country=? WHERE id=?", tourist.getName(), tourist.getLocation(), tourist.getCountry(), id);
  if (n == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Tourist not found"));
  return ResponseEntity.ok(Map.of("message","Tourist updated successfully"));
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<?> delete(@RequestHeader(value="Token", required=false) String token, @PathVariable long id) {
  if (!validToken(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Token Validation Failed"));
  int n = jdbc.update("DELETE FROM tourists WHERE id=?", id);
  if (n == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Tourist not found"));
  return ResponseEntity.ok(Map.of("message","Tourist deleted successfully"));
 }
 public static class Tourist {
  private String name, location, country;
  public Tourist() {}
  public String getName(){return name;} public void setName(String name){this.name=name;}
  public String getLocation(){return location;} public void setLocation(String location){this.location=location;}
  public String getCountry(){return country;} public void setCountry(String country){this.country=country;}
 }
}