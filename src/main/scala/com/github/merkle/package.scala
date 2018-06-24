package com.github

package object merkle {

  /** Type for representing block like an array of bytes. **/
  type Block = Array[Byte]

  /** Type for mapping block to a result of hash digest. **/
  type Digest = Block => Block

  /** Plain string to bytes array **/
  implicit def stringToBytesArray(string: String): Block = string.getBytes()
}
