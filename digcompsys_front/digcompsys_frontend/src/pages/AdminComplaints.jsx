import { useEffect, useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function AdminComplaints() {
  const [complaints, setComplaints] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    API.get("/complaints")
      .then((res) => {
        const sorted = [...res.data].reverse();
        setComplaints(sorted);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="container mt-5">
      <h2>All Complaints</h2>

      <table className="table mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {complaints.map((c) => (
            <tr key={c.complaintId}>
              <td>{c.complaintId}</td>
              <td>{c.title}</td>
              <td>{c.status}</td>

              <td>
                <button
                  className="btn btn-info btn-sm m-1"
                  onClick={() => navigate(`/complaint/${c.complaintId}`)}
                >
                  View
                </button>

                <button
                  className="btn btn-warning btn-sm m-1"
                  onClick={() => navigate(`/status/${c.complaintId}`)}
                >
                  Status
                </button>

                <button
                  className="btn btn-success btn-sm m-1"
                  onClick={() => navigate(`/assign/${c.complaintId}`)}
                >
                  Assign
                </button>
              </td>

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminComplaints;