package klaa.mouataz.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("staff", r -> r.path("/api/v1/staffs/**")
//                        .filters(f -> f.filter(filter))
                        .uri("lb://STAFF"))
                .route("notification", r -> r.path("/api/v1/notifications/**")
//                        .filters(f -> f.filter(filter))
                        .uri("lb://NOTIFICATION"))
                .route("courses", r -> r.path("/api/v1/courses/**")
                 //       .filters(f -> f.filter(filter))
                        .uri("lb://COURSES"))
                .route("user", r -> r.path("/api/v1/auth/**")
                        .uri("lb://USER"))
                .route("user", r -> r.path("/api/v1/users/**")
                    //    .filters(f -> f.filter(filter))
                        .uri("lb://USER"))
                .route("video", r -> r.path("/api/v1/videos/**")
                   //     .filters(f -> f.filter(filter))
                        .uri("lb://VIDEO"))
                .route("video", r -> r.path("/api/v1/videoinfos/**")
//                        .filters(f -> f.filter(filter))
                        .uri("lb://VIDEO"))
                .route("documents", r -> r.path("/api/v1/documents/**")
//                        .filters(f -> f.filter(filter))
                        .uri("lb://DOCUMENTS"))
                .route("aggregator", r -> r.path("/api/v1/aggregator/**")
                        .uri("lb://AGGREGATOR"))
                .build();
    }


}
