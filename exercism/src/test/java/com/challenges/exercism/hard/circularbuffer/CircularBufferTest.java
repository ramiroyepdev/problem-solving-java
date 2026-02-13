package com.challenges.exercism.hard.circularbuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CircularBufferTest {

  @Test
  @DisplayName("reading empty buffer should fail")
  void readingFromEmptyBufferShouldThrowException() {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    assertThatExceptionOfType(BufferIOException.class)
        .isThrownBy(buffer::read)
        .withMessage("Tried to read from empty buffer");
  }

  @Test
  @DisplayName("can read an item just written")
  void canReadItemJustWritten() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.write(1);

    assertThat(buffer.read()).isEqualTo(1);
  }

  @Test
  @DisplayName("each item may only be read once")
  void canReadItemOnlyOnce() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.write(1);

    assertThat(buffer.read()).isEqualTo(1);

    assertThatExceptionOfType(BufferIOException.class)
        .isThrownBy(buffer::read)
        .withMessage("Tried to read from empty buffer");
  }

  @Test
  @DisplayName("items are read in the order they are written")
  void readsItemsInOrderWritten() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(2);

    buffer.write(1);

    buffer.write(2);

    assertThat(buffer.read()).isEqualTo(1);

    assertThat(buffer.read()).isEqualTo(2);
  }

  @Test
  @DisplayName("full buffer can't be written to")
  void fullBufferCantBeWrittenTo() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.write(1);

    assertThatExceptionOfType(BufferIOException.class)
        .isThrownBy(() -> buffer.write(2))
        .withMessage("Tried to write to full buffer");
  }

  @Test
  @DisplayName("a read frees up capacity for another write")
  void readFreesUpSpaceForWrite() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.write(1);

    assertThat(buffer.read()).isEqualTo(1);

    buffer.write(2);

    assertThat(buffer.read()).isEqualTo(2);
  }

  @Test
  @DisplayName("read position is maintained even across multiple writes")
  void maintainsReadPositionAcrossWrites() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

    buffer.write(1);

    buffer.write(2);

    assertThat(buffer.read()).isEqualTo(1);

    buffer.write(3);

    assertThat(buffer.read()).isEqualTo(2);

    assertThat(buffer.read()).isEqualTo(3);
  }

  @Test
  @DisplayName("items cleared out of buffer can't be read")
  void cantReadClearedItems() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.write(1);

    buffer.clear();

    assertThatExceptionOfType(BufferIOException.class)
        .isThrownBy(buffer::read)
        .withMessage("Tried to read from empty buffer");
  }

  @Test
  @DisplayName("clear frees up capacity for another write")
  void clearFreesUpCapacity() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.write(1);

    buffer.clear();

    buffer.write(2);

    assertThat(buffer.read()).isEqualTo(2);
  }

  @Test
  @DisplayName("clear does nothing on empty buffer")
  void clearDoesNothingOnEmptyBuffer() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(1);

    buffer.clear();

    buffer.write(1);

    assertThat(buffer.read()).isEqualTo(1);
  }

  @Test
  @DisplayName("overwrite acts like write on non-full buffer")
  void overwriteActsLikeWriteOnNonFullBuffer() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(2);

    buffer.write(1);

    buffer.overwrite(2);

    assertThat(buffer.read()).isEqualTo(1);

    assertThat(buffer.read()).isEqualTo(2);
  }

  @Test
  @DisplayName("overwrite replaces the oldest item on full buffer")
  void overwriteRemovesOldestElementOnFullBuffer() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(2);

    buffer.write(1);

    buffer.write(2);

    buffer.overwrite(3);

    assertThat(buffer.read()).isEqualTo(2);

    assertThat(buffer.read()).isEqualTo(3);
  }

  @Test
  @DisplayName("overwrite replaces the oldest item remaining in buffer following a read")
  void overwriteDoesntRemoveAnAlreadyReadElement() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

    buffer.write(1);

    buffer.write(2);

    buffer.write(3);

    assertThat(buffer.read()).isEqualTo(1);

    buffer.write(4);

    buffer.overwrite(5);

    assertThat(buffer.read()).isEqualTo(3);

    assertThat(buffer.read()).isEqualTo(4);

    assertThat(buffer.read()).isEqualTo(5);
  }

  @Test
  @DisplayName("initial clear does not affect wrapping around")
  void initialClearDoesNotAffectWrappingAround() throws BufferIOException {

    CircularBuffer<Integer> buffer = new CircularBuffer<>(2);

    buffer.clear();

    buffer.write(1);

    buffer.write(2);

    buffer.overwrite(3);

    buffer.overwrite(4);

    assertThat(buffer.read()).isEqualTo(3);

    assertThat(buffer.read()).isEqualTo(4);

    assertThatExceptionOfType(BufferIOException.class)
        .isThrownBy(buffer::read)
        .withMessage("Tried to read from empty buffer");
  }
}
