import { useEffect, useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function EmployeeComplaints() {
  const [complaints, setComplaints] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    API.get("/complaints/employee")
      .then(res => setComplaints(res.data))
      .catch(err => console.log(err));
  }, []);

  return (
    <div className="container mt-5">
      <h2>Assigned Complaints</h2>

      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {complaints.map(c => (
            <tr key={c.complaintId}>
              <td>{c.complaintId}</td>
              <td>{c.title}</td>
              <td>{c.status}</td>

              <td>
                <button onClick={() => navigate(`/complaint/${c.complaintId}`)} className="btn btn-info btn-sm m-1">
                  View
                </button>

                <button onClick={() => navigate(`/update-status/${c.complaintId}`)} className="btn btn-warning btn-sm m-1">
                  Status
                </button>

                <button onClick={() => navigate(`/notify/${c.complaintId}`)} className="btn btn-success btn-sm m-1">
                  Notify
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default EmployeeComplaints;