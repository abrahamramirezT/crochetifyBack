package utez.edu.mx.crochetifyBack.services.review;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.product.ProductCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.review.ReviewCreateRequest;

public interface ReviewService {
    ResponseObject createReview(ReviewCreateRequest request);
}
