package com.challenges.exercism.hard.circularbuffer;

import java.util.Arrays;

public class CircularBuffer<T> {

  private final Object[] buffer;
  private int firstElement = 0;
  private int amountInBuffer = 0;

  CircularBuffer(final int size) {
    this.buffer = new Object[size];
  }

  @SuppressWarnings("unchecked")
  T read() throws BufferIOException {
    if (this.amountInBuffer == 0) {
      throw new BufferIOException("Tried to read from empty buffer");
    }

    T data = (T) this.buffer[this.firstElement];
    this.buffer[this.firstElement] = null;
    this.firstElement = (this.firstElement + 1) % this.buffer.length;
    this.amountInBuffer--;
    return data;
  }

  void write(T data) throws BufferIOException {

    if (this.amountInBuffer == this.buffer.length) {
      throw new BufferIOException("Tried to write to full buffer");
    }

    this.writeImplementation(data);
  }

  private void writeImplementation(T data) {
    this.buffer[(this.firstElement + this.amountInBuffer) % this.buffer.length] = data;
    this.amountInBuffer++;
  }

  void overwrite(T data) {
    if (this.amountInBuffer == this.buffer.length) {
      this.buffer[this.firstElement] = data;
      this.firstElement = (this.firstElement + 1) % this.buffer.length;
    } else {
      this.writeImplementation(data);
    }
  }

  void clear() {
    Arrays.fill(this.buffer, null);
    this.amountInBuffer = 0;
  }
}
