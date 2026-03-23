import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import API from "../services/api";

function ComplaintDetails() {
  const { id } = useParams();
  const [complaint, setComplaint] = useState(null);

  useEffect(() => {
    API.get(`/complaints/${id}`)
      .then((res) => setComplaint(res.data))
      .catch((err) => console.log(err));
  }, [id]);

  if (!complaint) return <p>Loading...</p>;

  return (
    <div className="container mt-5">
      <h2>Complaint Details</h2>

      <p><b>Title:</b> {complaint.title}</p>
      <p><b>Description:</b> {complaint.description}</p>
      <p><b>Status:</b> {complaint.status}</p>
    </div>
  );
}

export default ComplaintDetails;