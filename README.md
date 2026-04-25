HireHub API Documentation
Introduction
HireHub is a Spring Boot-based recruitment platform backend. It facilitates interactions between Candidates seeking jobs, Companies posting vacancies, and Admins managing the ecosystem.

Base URLs
Admin API: /api/admin

Authentication API: /api/auth

Candidate API: /api/candidate

Company API: /api/company

Job Posting API: /api/job_posting

1. Authentication Endpoints
   These endpoints are used for user login and obtaining access tokens.

Login (User)

Endpoint: POST /api/auth/login

Body: AuthRequest (email, password)

Purpose: Authenticates Candidates and Companies.

Login (Admin)

Endpoint: POST /api/auth/login_admin

Body: AuthRequest (email, password)

Purpose: Authenticates Administrative users.

2. Admin Endpoints
   Requires Admin privileges.

Change User Status

Endpoint: PUT /api/admin/change_status/{id}

Params: status (Query)

Purpose: Activates or deactivates a user account.

Change Job Post Status

Endpoint: PUT /api/admin/change_status_job_post/{jobPostId}

Params: status (Query)

Purpose: Approves or rejects a submitted job posting.

Get Users by Status

Endpoint: GET /api/admin/all_users_by_status

Params: status (Query)

Get Users by Role

Endpoint: GET /api/admin/all_users_by_role

Params: role (Query)

3. Candidate Endpoints
   Functionalities for job seekers.

Register Candidate

Endpoint: POST /api/candidate/register

Body: CandidateRegisterRequest

Update Profile

Endpoint: PUT /api/candidate/update

Auth: Required (JWT)

Body: CandidateUpdateRequest

Delete Profile

Endpoint: PUT /api/candidate/delete

Auth: Required (JWT)

View Active Jobs

Endpoint: GET /api/candidate/all_job_posts

Purpose: Lists all job posts currently approved and active.

Apply for Job

Endpoint: POST /api/candidate/apply_job/{id}

Purpose: Sends an application for a specific job ID.

View My Applications

Endpoint: GET /api/candidate/get_applications

Purpose: Returns a list of jobs the candidate has applied for.

4. Company Endpoints
   Functionalities for employers.

Register Company

Endpoint: POST /api/company/register

Body: CompanyRegisterRequest

Update Company Profile

Endpoint: PUT /api/company/update

Auth: Required (JWT)

Delete Company Profile

Endpoint: DELETE /api/company/delete

View All Candidates

Endpoint: GET /api/company/all_candidates

Purpose: Allows companies to browse registered candidates.

View Received Applications

Endpoint: GET /api/company/all_applications

Purpose: Shows candidates who applied to the company's job posts.

Manage Application Status

Endpoint: PUT /api/company/change_status_application/{id}

Params: status (Query)

Purpose: Accept or reject a candidate's application.

5. Job Posting Endpoints
   Managing vacancies.

Create Job Post

Endpoint: POST /api/job_posting/create

Auth: Required (Company Only)

Body: JobPostingCreateRequest

Update Job Post

Endpoint: PUT /api/job_posting/update

Params: id (Query)

Body: JobPostingUpdateRequest

Delete Job Post

Endpoint: DELETE /api/job_posting/delete

Params: jobPostId (Query)

Error Handling & Exceptions
The application uses a Global Exception Handler to return consistent error responses. All error responses follow this structure:

Timestamp: Time of the error.

Path: The endpoint that caused the error.

Message: A summary of the issue.

Details: A map containing specific error info (e.g., validation field errors).

Common Exceptions and Status Codes:
400 Bad Request

MethodArgumentNotValidException: Triggered when @Valid input fails (e.g., empty fields, wrong format).

IncorrectAgeException: Triggered if the candidate is younger than 16.

MismatchCompanyApplicationException: Triggered when a company tries to access an application that doesn't belong to its job posts.

401 Unauthorized

IncorrectPasswordException: Triggered during login if the password doesn't match.

404 Not Found

UserNotFoundException: General user not found.

CandidateNotFoundException: Specific candidate profile missing.

CompanyNotFoundException: Specific company profile missing.

JobPostingNotFoundException: The requested job post does not exist.

ApplicationNotFoundException: The job application record was not found.

409 Conflict

AlreadyExistsException: Triggered when trying to register an email or entity that already exists.

AlreadyDoneBeforeException: Triggered when an action (like a specific application or status change) has already been performed.

500 Internal Server Error

Exception: Catch-all for any unhandled system errors ("something went wrong").