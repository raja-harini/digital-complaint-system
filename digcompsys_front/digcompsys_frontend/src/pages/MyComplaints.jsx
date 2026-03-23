import { useEffect, useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function MyComplaints() {
  const [complaints, setComplaints] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchComplaints = async () => {
      try {
        const res = await API.get("/complaints/my");

        const sorted = res.data.sort(
          (a, b) => new Date(b.createdAt) - new Date(a.createdAt)
        );

        setComplaints(sorted);
      } catch (error) {
        console.log(error);
      }
    };

    fetchComplaints();
  }, []);

  return (
    <div className="container mt-5">
      <h2>My Complaints</h2>

      <table className="table table-bordered mt-3">
        <thead>
          <tr>
            <th>Title</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {complaints.map((c) => (
            <tr key={c.complaintId}>
              <td>{c.title}</td>
              <td>{c.status}</td>

              <td>
                <button
                  onClick={() => navigate(`/complaint/${c.complaintId}`)}
                  className="btn btn-info btn-sm m-1"
                >
                  View
                </button>

                <button
                  onClick={() => navigate(`/status/${c.complaintId}`)}
                  className="btn btn-warning btn-sm m-1"
                >
                  Status History
                </button>

                <button
                  onClick={() => navigate(`/query/${c.complaintId}`)}
                  className="btn btn-danger btn-sm m-1"
                >
                  Raise Query
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default MyComplaints;