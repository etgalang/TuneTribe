const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const PORT = 3000;

let reports = [];  // Temporary storage for reports

app.use(cors()); // Enable CORS for all routes
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static('public')); // Ensure this is correctly pointing to your static files

// Endpoint to handle report submissions
app.post('/submit-report', (req, res) => {
    console.log('Received report:', req.body);  // Log the received report
    const report = req.body;
    reports.push(report); // Save report in memory
    console.log('Current reports:', reports);  // Log all current reports
    res.status(200).send({ success: true, message: 'Report submitted successfully' });
});

// Endpoint to get all reports
app.get('/get-reports', (req, res) => {
    res.status(200).json(reports);
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
