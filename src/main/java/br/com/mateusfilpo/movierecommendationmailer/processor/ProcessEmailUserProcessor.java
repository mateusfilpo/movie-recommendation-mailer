package br.com.mateusfilpo.movierecommendationmailer.processor;

import br.com.mateusfilpo.movierecommendationmailer.model.Movie;
import br.com.mateusfilpo.movierecommendationmailer.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class ProcessEmailUserProcessor implements ItemProcessor<User, SimpleMailMessage> {

    @Override
    public SimpleMailMessage process(User user) throws Exception {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("xpto@no-reply.com");
        email.setTo(user.getEmail());
        email.setSubject("Top Movie Picks Based on Your Preferences");
        email.setText(generateText(user));
        Thread.sleep(2000);
        return email;
    }

    private String generateText(User user) {
        StringBuilder writer = new StringBuilder();
        writer.append(String.format("Hello, %s %s!\n\n", user.getFirstName(), user.getLastName()));
        writer.append("We thought you might be interested in these movie recommendations:\n\n");

        for (Movie movie : user.getMovies()) {
            writer.append(String.format("%s\n\n", movie.getTitle()));
        }

        writer.append("We hope you enjoy watching them!\n");

        return writer.toString();
    }
}
