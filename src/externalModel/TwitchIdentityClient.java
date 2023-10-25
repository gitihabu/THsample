package external.model;

public interface TwitchIdentityClient {
    @PostExchange(url = "/oauth2/token", contentType = "application/x-www-form-urlencoded")
    TwitchToken requestAccessToken(@RequestParam("client_id") String clientId,
                                   @RequestParam("client_secret") String clientSecret,
                                   @RequestParam("grant_type") String grantType);
    @Bean
    public TwitchIdentityClient twitchIdentityClient() {
        WebClient client = WebClient.builder()
                .baseUrl("https://id.twitch.tv/")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(TwitchIdentityClient.class);
    }

    @Bean
    public TwitchApiClient twitchApiClient(@Value("${twitch.client-id}") String twitchClientId) {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.twitch.tv")
                .defaultHeader("Client-Id", twitchClientId)
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(TwitchApiClient.class);
    }


}
