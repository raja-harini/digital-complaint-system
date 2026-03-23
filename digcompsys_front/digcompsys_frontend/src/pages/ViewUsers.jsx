import { useEffect, useState } from "react";
import API from "../services/api";

function ViewUsers() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    API.get("/users/admin") // ✅ FIXED URL
      .then(res => setUsers(res.data))
      .catch(err => console.log(err));
  }, []);

  return (
    <div className="container mt-5">
      <h2>All Users</h2>

      <table className="table mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
          </tr>
        </thead>

        <tbody>
          {users.map(u => (
            <tr key={u.userId}>
              <td>{u.userId}</td>
              <td>{u.userName}</td>
              <td>{u.email}</td>
              <td>{u.roleName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewUsers;