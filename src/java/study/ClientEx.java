public class ClientEx {
  
  public static voiod main(String[] args) throws Exception {
    
    HttpClient httpClient = new HttpClient(); //jetty client
    httpClient.start();
    
    ContentResponse contentResposne = httpClient.newRequest("http://127.0.0.1:8080/mypath")
                                                .method(HttpMethod.GET)
                                                .send();
    
    System.out.println(contentResponse.getContentAsString());
    
  }
}
