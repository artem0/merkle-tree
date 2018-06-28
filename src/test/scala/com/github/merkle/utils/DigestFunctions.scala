package com.github.merkle.utils

import java.security.MessageDigest

import com.github.merkle.Block

object DigestFunctions {

  def md5Digest(b: Array[Byte]): Block = {
    MessageDigest.getInstance("MD5").digest(b)
  }

  def sha1Digest(b: Array[Byte]): Block = {
    MessageDigest.getInstance("SHA1").digest(b)
  }

}
