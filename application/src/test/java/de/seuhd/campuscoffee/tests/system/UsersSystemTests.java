package de.seuhd.campuscoffee.tests.system;

import de.seuhd.campuscoffee.domain.model.Pos;
import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import org.junit.jupiter.api.Test;
import java.util.List;


import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.posRequests;
import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.userRequests;

public class UsersSystemTests extends AbstractSysTest {

    //TODO: Uncomment once user endpoint is implemented

    @Test
    void createUser() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

        assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
    }

    //TODO: Add at least two additional tests for user operations

    @Test
    void getAllCreatedPos() {
        List<Pos> createdPosList = TestFixtures.createPosFixtures(posService);

        List<Pos> retrievedPos = posRequests.retrieveAll()
                .stream()
                .map(posDtoMapper::toDomain)
                .toList();

        assertEqualsIgnoringTimestamps(retrievedPos, createdPosList);
    }

    @Test
    void getPosById() {
        List<Pos> createdPosList = TestFixtures.createPosFixtures(posService);
        Pos createdPos = createdPosList.getFirst();

        Pos retrievedPos = posDtoMapper.toDomain(
                posRequests.retrieveById(createdPos.id())
        );

        assertEqualsIgnoringTimestamps(retrievedPos, createdPos);
    }

}