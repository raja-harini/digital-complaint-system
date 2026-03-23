import { useEffect, useState } from "react";
import API from "../services/api";

function ViewEmployees() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    API.get("/employee-details/admin")
      .then(res => setEmployees(res.data))
      .catch(err => console.log(err));
  }, []);

  const handleView = async (userId) => {
    try {
      const response = await API.get(
        `/employee-details/document/${userId}`,
        {
          responseType: "blob",
        }
      );

      // ✅ IMPORTANT: set correct file type (PDF)
      const file = new Blob([response.data], {
        type: "application/pdf",
      });

      const fileURL = window.URL.createObjectURL(file);

      // ✅ open in new tab
      window.open(fileURL);

    } catch (err) {
      console.log(err);
      alert("Unable to open document");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Employees</h2>

      <table className="table mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Field</th>
            <th>Role</th>
            <th>Document</th>
          </tr>
        </thead>

        <tbody>
          {employees.map((emp, index) => (
            <tr key={emp.userId || index}>
              <td>{emp.userId}</td>
              <td>{emp.userName}</td>
              <td>{emp.email}</td>
              <td>{emp.fieldOfWork || "-"}</td>
              <td>{emp.preferredRole}</td>

              <td>
                {emp.documentUrl ? (
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => handleView(emp.userId)}
                  >
                    View Proof
                  </button>
                ) : (
                  <span>No File</span>
                )}
              </td>

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewEmployees;