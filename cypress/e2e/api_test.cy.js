describe('Backend API testing',()=>
{
    it('Get Projects',()=>
    {
        cy.request('GET', 'http://localhost:8081/projects')
            .then( (response) => 
            {
                // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#successful_responses
                // 200 OK
                // The request succeeded. 
                // The result meaning of "success" depends on the HTTP method:
                // GET: The resource has been fetched and transmitted in the message body.
                expect(response.status).to.eq(200)
                expect(response.body).to.have.length(1)
            })
    })
})