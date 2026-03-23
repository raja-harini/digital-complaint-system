import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API from "../services/api";

function ComplaintStatus() {
  const { id } = useParams();
  const [history, setHistory] = useState([]);

  useEffect(() => {
    API.get(`/complaints/${id}/history`)
      .then((res) => {
        console.log("STATUS HISTORY:", res.data);
        setHistory(Array.isArray(res.data) ? res.data : []);
      })
      .catch((err) => console.log(err));
  }, [id]);

  return (
    <div className="container mt-5">
      <h2>Complaint Status Timeline</h2>

      {history.length === 0 ? (
        <p>No history available</p>
      ) : (
        <div className="mt-4">
          {history.map((h, i) => (
            <div key={i} className="border p-3 mb-3 rounded shadow-sm">

              <h5>{h.newStatus}</h5>

              <small>
                <b>By:</b> {h.user?.userName || "Unknown"}
              </small>
              <br />

              <small>
                <b>Time:</b> {h.createdAt}
              </small>

            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default ComplaintStatus;