# Speer
Back End Assessment
<hr>

<h3> Branch Information </h3>
<ol>
<li><b>setup/project:</b> initial spring project setup </li>
<li><b>feature/signup:</b> developed an API Endpoint for <code> Create a new user account </code> </li>
<li><b>feature/authentication:</b> using jwt tokens for authentication <code> Login to an existing user account and receive a acess token </code></li>
<li><b>feature/notes:</b> logged in user is able to post notes in the database <code> Create a new note for the authenticated user </code></li>
<li><b>feature/fetchallnotes:</b> logged in user is able to fetch the notes posted by him/her from db <code> Get a list of all notes for the authenticated user </code></li>
<li><b>feature/notebyid:</b> logged in user is able to fetch the notes posted by him/her from db by note id <code> Get a note by ID for the authenticated user </code></li>
<li><b>feature/updateExistingNote:</b> logged in user is able to update the notes posted by him/her from db by note id <code> Update an existing note by ID for the authenticated user </code></li>
<li><b>feature/deletNoteById:</b> logged in user is able to delete the notes posted by him/her from db by note id <code> Delete a note by ID for the authenticated user </code></li>
<li><b>feature/searchBasedOnQuery:</b> logged in user is able to search the notes posted by him/her from db by search query <code> Search for notes based on keywords for the authenticated user. </code></li>
</ol>

<h3>Project Setup</h3>
<ol>
  <li>Create a folder locally.</li>
  <li>Clone 2 git repositories - eureka (https://github.com/Parikshit97/eureka) and Speer.</li>
  <li>Setup the projects in IntelliJ.</li>
  <li>Run eureka (server) first and then Speer</li>
</ol>

<h3>API testing - Curls</h3>
<i>Please change as per requirement. First you will need to sign up, it will give you the token, use that token to login and perform all operations as mentioned in assessment.</i>

<b>1. Create a new user account</b><br>
<code>
  curl --location 'http://localhost:8080/api/auth/signup' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA' \
--data-raw '{
          "firstName" : "abc",
          "lastName" : "def",
          "userName" : "abcdef",
          "password" : "abc@def",
          "email" : "abc@ndef.edu"
        }
'
</code> <br>

<b>2. Login to an existing user account and receive a acess token</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA' \
--data-raw '{
          "userName" : "abc",
          "password" : "abc@def",
          "email" : "abc@def.edu"
        }'
</code>

<b>3. Create a new note for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location 'http://localhost:8080/api/notes' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA' \
--data '{
          "title" : "A poetry",
          "content" : "Lorem cat, Bdoglah Blah"
}'
</code>

<b>4. Get a list of all notes for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location 'http://localhost:8080/api/notes' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA'
</code>

<b>5. Get a note by ID for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location 'http://localhost:8080/api/notes/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA'
</code>

<b>6. Update an existing note by ID for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location --request PUT 'http://localhost:8080/api/notes/1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA' \
--data '{
          "title" : "updated",
          "content" : "updated"
        }'
</code>

<b>7. Delete a note by ID for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location --request DELETE 'http://localhost:8080/api/notes/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA'
</code>

<b>8. Search for notes based on keywords for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location 'http://localhost:8080/api/search?q=fish' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzNDQ1NjksImV4cCI6MTcwNDM0NjAwOX0.ZINpbqnxsYQMQJL99MBI-dySdcgiPY2-mQvFNFtyXUs' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA'
</code>

<b>9. Share a note with another user for the authenticated user</b> <h4> <i>Change the authorization bearer token for the following requests with the output of above request. </i> </h4>
<code>
curl --location --request POST 'http://localhost:8080/api/notes/3/share?sharedWithUserId=1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpa3NoaXQiLCJpYXQiOjE3MDQzMzY2NjcsImV4cCI6MTcwNDMzODEwN30.eqzP-XOFHcAACOUS3lxVCv0uXkm04kGR9I6XkimTbRA' \
--header 'Cookie: JSESSIONID=E6EBE9F41C184F3C03966FB70AD944EA'
</code>




















