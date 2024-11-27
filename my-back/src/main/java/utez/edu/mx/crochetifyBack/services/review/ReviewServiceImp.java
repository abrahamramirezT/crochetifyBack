package utez.edu.mx.crochetifyBack.services.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.review.ReviewCreateRequest;
import utez.edu.mx.crochetifyBack.entities.Product;
import utez.edu.mx.crochetifyBack.entities.Review;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.repositories.ProductRepository;
import utez.edu.mx.crochetifyBack.repositories.ReviewRepository;

import java.util.HashSet;
import java.util.Set;


@Service
public class ReviewServiceImp  implements ReviewService{
    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImp.class);

        @Autowired
        private ReviewRepository reviewRepository;

        @Autowired
        private ProductRepository productRepository;


    @Override
    public ResponseObject createReview(ReviewCreateRequest request) {
        try {

            Set<Product> products = new HashSet<>(productRepository.findAllById(request.getProductIds()));

            Review review = Review.builder()
                    .comment(request.getComment())
                    .score(request.getScore())
                    .product(products)
                    .build();

            reviewRepository.save(review);

            return new ResponseObject(true, "Review registrada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar la review: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar la review");
        }
    }
}
