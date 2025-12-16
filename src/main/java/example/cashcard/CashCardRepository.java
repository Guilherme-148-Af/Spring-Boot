/* package example.cashcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface CashCardRepository extends CrudRepository<CashCard, Long>, PagingAndSortingRepository<CashCard, Long> {
    
    boolean existsByIdAndOwner(Long id, String owner);

	CashCard findByIdAndOwner(Long requestedId, String name);

	Page<CashCard> findByOwner(String name, PageRequest of);
    
} */

package example.cashcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    Page<Message> findByToUserOrderByCreatedAtDesc(String toUser, Pageable pageable);

    Page<Message> findByFromUserOrderByCreatedAtDesc(String fromUser, Pageable pageable);
}