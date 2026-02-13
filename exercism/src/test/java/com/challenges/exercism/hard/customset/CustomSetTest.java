package com.challenges.exercism.hard.customset;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomSetTest {

  @Test
  @DisplayName("Returns true if the set contains no elements")
  void setsWithNoElementsAreEmpty() {

    CustomSet<Integer> customSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("sets with elements are not empty")
  void setsWithElementsAreNotEmpty() {

    CustomSet<Character> customSet = new CustomSet<>(Collections.singletonList('1'));

    assertThat(customSet.isEmpty()).isFalse();
  }

  @Test
  @DisplayName("nothing is contained in an empty set")
  void nothingIsContainedInAnEmptySet() {

    CustomSet<String> customSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.contains("1")).isFalse();
  }

  @Test
  @DisplayName("when the element is in the set")
  void whenTheElementIsInTheSet() {

    CustomSet<Integer> customSet = new CustomSet<>(Arrays.asList(1, 2, 3));

    assertThat(customSet.contains(1)).isTrue();
  }

  @Test
  @DisplayName("when the element is not in the set")
  void whenTheElementIsNotInTheSet() {

    CustomSet<Character> customSet = new CustomSet<>(Arrays.asList('1', '2', '3'));

    assertThat(customSet.contains('4')).isFalse();
  }

  @Test
  @DisplayName("empty set is a subset of another empty set")
  void emptySetIsASubsetOfAnotherEmptySet() {

    CustomSet<String> customSet = new CustomSet<>(Collections.emptyList());

    CustomSet<String> secondCustomSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.isSubset(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("empty set is a subset of non-empty set")
  void emptySetIsASubsetOfNonEmptySet() {

    CustomSet<Integer> customSet = new CustomSet<>(Collections.singletonList(1));

    CustomSet<Integer> secondCustomSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.isSubset(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("non-empty set is not a subset of empty set")
  void nonEmptySetIsNotASubsetOfEmptySet() {

    CustomSet<Character> customSet = new CustomSet<>(Collections.emptyList());

    CustomSet<Character> secondCustomSet = new CustomSet<>(Collections.singletonList('1'));

    assertThat(customSet.isSubset(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("set is a subset of set with exact same elements")
  void setIsASubsetOfSetWithExactSameElements() {

    CustomSet<String> customSet = new CustomSet<>(Arrays.asList("1", "2", "3"));

    CustomSet<String> secondCustomSet = new CustomSet<>(Arrays.asList("1", "2", "3"));

    assertThat(customSet.isSubset(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("set is a subset of larger set with same elements")
  void setIsASubsetOfLargerSetWithSameElements() {

    CustomSet<Integer> customSet = new CustomSet<>(Arrays.asList(4, 1, 2, 3));

    CustomSet<Integer> secondCustomSet = new CustomSet<>(Arrays.asList(1, 2, 3));

    assertThat(customSet.isSubset(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("set is not a subset of set that does not contain its elements")
  void setIsNotASubsetOfSetThatDoesNotContainItsElements() {

    CustomSet<Character> customSet = new CustomSet<>(Arrays.asList('4', '1', '3'));

    CustomSet<Character> secondCustomSet = new CustomSet<>(Arrays.asList('1', '2', '3'));

    assertThat(customSet.isSubset(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("the empty set is disjoint with itself")
  void theEmptySetIsDisjointWithItself() {

    CustomSet<String> customSet = new CustomSet<>(Collections.emptyList());

    CustomSet<String> secondCustomSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.isDisjoint(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("empty set is disjoint with non-empty set")
  void emptySetIsDisjointWithNonEmptySet() {

    CustomSet<Integer> customSet = new CustomSet<>(Collections.emptyList());

    CustomSet<Integer> secondCustomSet = new CustomSet<>(Collections.singletonList(1));

    assertThat(customSet.isDisjoint(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("non-empty set is disjoint with empty set")
  void nonEmptySetIsDisjointWithEmptySet() {

    CustomSet<Character> customSet = new CustomSet<>(Collections.singletonList('1'));

    CustomSet<Character> secondCustomSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.isDisjoint(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("sets are not disjoint if they share an element")
  void setsAreNotDisjointIfTheyShareAnElement() {

    CustomSet<String> customSet = new CustomSet<>(Arrays.asList("1", "2"));

    CustomSet<String> secondCustomSet = new CustomSet<>(Arrays.asList("2", "3"));

    assertThat(customSet.isDisjoint(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("sets are disjoint if they share no elements")
  void setsAreDisjointIfTheyShareNoElements() {

    CustomSet<Integer> customSet = new CustomSet<>(Arrays.asList(1, 2));

    CustomSet<Integer> secondCustomSet = new CustomSet<>(Arrays.asList(3, 4));

    assertThat(customSet.isDisjoint(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("empty sets are equal")
  void emptySetsAreEqual() {

    CustomSet<Character> customSet = new CustomSet<>(Collections.emptyList());

    CustomSet<Character> secondCustomSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.equals(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("empty set is not equal to non-empty set")
  void emptySetIsNotEqualToNonEmptySet() {

    CustomSet<String> customSet = new CustomSet<>(Collections.emptyList());

    CustomSet<String> secondCustomSet = new CustomSet<>(Arrays.asList("1", "2", "3"));

    assertThat(customSet.equals(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("non-empty set is not equal to empty set")
  void nonEmptySetIsNotEqualToEmptySet() {

    CustomSet<Integer> customSet = new CustomSet<>(Arrays.asList(1, 2, 3));

    CustomSet<Integer> secondCustomSet = new CustomSet<>(Collections.emptyList());

    assertThat(customSet.equals(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("sets with the same elements are equal")
  void setsWithTheSameElementsAreEqual() {

    CustomSet<Character> customSet = new CustomSet<>(Arrays.asList('1', '2'));

    CustomSet<Character> secondCustomSet = new CustomSet<>(Arrays.asList('2', '1'));

    assertThat(customSet.equals(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("sets with different elements are not equal")
  void setsWithDifferentElementsAreNotEqual() {

    CustomSet<String> customSet = new CustomSet<>(Arrays.asList("1", "2", "3"));

    CustomSet<String> secondCustomSet = new CustomSet<>(Arrays.asList("1", "2", "4"));

    assertThat(customSet.equals(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("set is not equal to larger set with same elements")
  void setIsNotEqualToLargerSetWithSameElements() {

    CustomSet<String> customSet = new CustomSet<>(Arrays.asList("1", "2", "3"));

    CustomSet<String> secondCustomSet = new CustomSet<>(Arrays.asList("1", "2", "3", "4"));

    assertThat(customSet.equals(secondCustomSet)).isFalse();
  }

  @Test
  @DisplayName("set is equal to a set constructed from an array with duplicates")
  void secondSetWithDuplicatesIsEqualToFirstSet() {

    CustomSet<String> customSet = new CustomSet<>(Collections.singletonList("1"));

    CustomSet<String> secondCustomSet = new CustomSet<>(Arrays.asList("1", "1"));

    assertThat(customSet.equals(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("difference removes all duplicates in the first set")
  void firstSetWithDuplicatesIsEqualToSecondSet() {

    CustomSet<String> customSet = new CustomSet<>(Arrays.asList("1", "1"));

    CustomSet<String> secondCustomSet = new CustomSet<>(Collections.singletonList("1"));

    assertThat(customSet.equals(secondCustomSet)).isTrue();
  }

  @Test
  @DisplayName("add to empty set")
  void addToEmptySet() {

    int element = 3;

    CustomSet<Integer> expected =
        new CustomSet<>(Collections.unmodifiableList(Collections.singletonList(element)));

    CustomSet<Integer> actual = new CustomSet<>(Collections.emptyList());

    actual.add(element);

    assertThat(actual).isNotNull();

    assertThat(actual.equals(expected)).isTrue();

    assertThat(actual.isEmpty()).isFalse();
  }

  @Test
  @DisplayName("add to non-empty set")
  void addToNonEmptySet() {

    char element = '3';

    CustomSet<Character> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList('1', '2', '3', '4')));

    CustomSet<Character> actual = new CustomSet<>(Arrays.asList('1', '2', '4'));

    actual.add(element);

    assertThat(actual).isNotNull();

    assertThat(actual.equals(expected)).isTrue();

    assertThat(actual.isEmpty()).isFalse();
  }

  @Test
  @DisplayName("adding an existing element does not change the set")
  void addingAnExistingElementDoesNotChangeTheSet() {

    String element = "3";

    CustomSet<String> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList("1", "2", "3")));

    CustomSet<String> actual = new CustomSet<>(Arrays.asList("1", "2", "3"));

    actual.add(element);

    assertThat(actual).isNotNull();

    assertThat(actual.equals(expected)).isTrue();
  }

  @Test
  @DisplayName("intersection of two empty sets is an empty set")
  void intersectionOfTwoEmptySetsIsAnEmptySet() {

    CustomSet<Integer> actual =
        new CustomSet<Integer>(Collections.emptyList())
            .getIntersection(new CustomSet<>(Collections.emptyList()));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("intersection of an empty set and non-empty set is an empty set")
  void intersectionOfAnEmptySetAndNonEmptySetIsAnEmptySet() {

    CustomSet<Character> actual =
        new CustomSet<Character>(Collections.emptyList())
            .getIntersection(new CustomSet<>(Arrays.asList('3', '2', '5')));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("intersection of a non-empty set and an empty set is an empty set")
  void intersectionOfANonEmptySetAndAnEmptySetIsAnEmptySet() {

    CustomSet<String> actual =
        new CustomSet<>(Arrays.asList("1", "2", "3", "4"))
            .getIntersection(new CustomSet<>(Collections.emptyList()));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("intersection of two sets with no shared elements is an empty set")
  void intersectionOfTwoSetsWithNoSharedElementsIsAnEmptySet() {

    CustomSet<Integer> actual =
        new CustomSet<>(Arrays.asList(1, 2, 3))
            .getIntersection(new CustomSet<>(Arrays.asList(4, 5, 6)));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("intersection of two sets with shared elements is a set of the shared elements")
  void intersectionOfTwoSetsWithSharedElementsIsASetOfTheSharedElements() {

    CustomSet<Character> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList('2', '3')));

    CustomSet<Character> actual =
        new CustomSet<>(Arrays.asList('1', '2', '3', '4'))
            .getIntersection(new CustomSet<>(Arrays.asList('3', '2', '5')));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isFalse();

    assertThat(actual.equals(expected)).isTrue();
  }

  @Test
  @DisplayName("difference of two empty sets is an empty set")
  void differenceOfTwoEmptySetsIsAnEmptySet() {

    CustomSet<String> actual =
        new CustomSet<String>(Collections.emptyList())
            .getDifference(new CustomSet<>(Collections.emptyList()));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("difference of empty set and non-empty set is an empty set")
  void differenceOfAnEmptySetAndNonEmptySetIsAnEmptySet() {

    CustomSet<Integer> actual =
        new CustomSet<Integer>(Collections.emptyList())
            .getDifference(new CustomSet<>(Arrays.asList(3, 2, 5)));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("difference of a non-empty set and an empty set is the non-empty set")
  void differenceOfANonEmptySetAndAnEmptySetIsTheNonEmptySet() {

    CustomSet<Character> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList('1', '2', '3', '4')));

    CustomSet<Character> actual =
        new CustomSet<>(Arrays.asList('1', '2', '3', '4'))
            .getDifference(new CustomSet<>(Collections.emptyList()));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isFalse();

    assertThat(actual.equals(expected)).isTrue();
  }

  @Test
  @DisplayName(
      "difference of two non-empty sets is a set of elements that are only in the first set")
  void differenceOfTwoNonEmptySetsIsASetOfElementsThatAreOnlyInTheFirstSet() {

    CustomSet<String> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList("1", "3")));

    CustomSet<String> actual =
        new CustomSet<>(Arrays.asList("3", "2", "1"))
            .getDifference(new CustomSet<>(Arrays.asList("2", "4")));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isFalse();

    assertThat(actual.equals(expected)).isTrue();
  }

  @Test
  @DisplayName("union of empty sets is an empty set")
  void unionOfTwoEmptySetsIsAnEmptySet() {

    CustomSet<Integer> actual =
        new CustomSet<Integer>(Collections.emptyList())
            .getUnion(new CustomSet<>(Collections.emptyList()));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("union of an empty set and non-empty set is the non-empty set")
  void unionOfAnEmptySetAndNonEmptySetIsTheNonEmptySet() {

    CustomSet<Character> expected =
        new CustomSet<>(Collections.unmodifiableList(Collections.singletonList('2')));

    CustomSet<Character> actual =
        new CustomSet<Character>(Collections.emptyList())
            .getUnion(new CustomSet<>(Collections.singletonList('2')));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isFalse();

    assertThat(actual.equals(expected)).isTrue();
  }

  @Test
  @DisplayName("union of a non-empty set and empty set is the non-empty set")
  void unionOfANonEmptySetAndAnEmptySetIsTheNonEmptySet() {

    CustomSet<String> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList("1", "3")));

    CustomSet<String> actual =
        new CustomSet<>(Arrays.asList("1", "3")).getUnion(new CustomSet<>(Collections.emptyList()));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isFalse();

    assertThat(actual.equals(expected)).isTrue();
  }

  @Test
  @DisplayName("union of non-empty sets contains all unique elements")
  void unionOfTwoNonEmptySetsContainsAllUniqueElements() {

    CustomSet<Integer> expected =
        new CustomSet<>(Collections.unmodifiableList(Arrays.asList(3, 2, 1)));

    CustomSet<Integer> actual =
        new CustomSet<>(Arrays.asList(1, 3)).getUnion(new CustomSet<>(Arrays.asList(2, 3)));

    assertThat(actual).isNotNull();

    assertThat(actual.isEmpty()).isFalse();

    assertThat(actual.equals(expected)).isTrue();
  }
}
