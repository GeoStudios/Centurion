/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
*/

#include <iostream>
#include <string>
#include <list>

#include "rdkafkacc_init.h"

/*
 * This file addes confimination for the rest of the C++ library.

 * @since Pre Development 1
 * @author Logan Abernathy
*/
RdKafka::ConfImpl::ConfResult RdKafka::ConfImpl::set(const std::string &name,
                                                     const std::string &value,
                                                     std::string &errstr) {
  rd_kafka_conf_res_t res;
  char errbuf[512];

  if (this->conf_type_ == CONF_GLOBAL)
    res = rd_kafka_conf_set(this->rk_conf_, name.c_str(), value.c_str(), errbuf,
                            sizeof(errbuf));
  else
    res = rd_kafka_topic_conf_set(this->rkt_conf_, name.c_str(), value.c_str(),
                                  errbuf, sizeof(errbuf));

  if (res != RD_KAFKA_CONF_OK)
    errstr = errbuf;

  return static_cast<Conf::ConfResult>(res);
}


std::list<std::string> *RdKafka::ConfImpl::dump() {
  const char **arrc;
  size_t cnt;
  std::list<std::string> *arr;

  if (rk_conf_)
    arrc = rd_kafka_conf_dump(rk_conf_, &cnt);
  else
    arrc = rd_kafka_topic_conf_dump(rkt_conf_, &cnt);

  arr = new std::list<std::string>();
  for (int i = 0; i < static_cast<int>(cnt); i++)
    arr->push_back(std::string(arrc[i]));

  rd_kafka_conf_dump_free(arrc, cnt);
  return arr;
}

RdKafka::Conf *RdKafka::Conf::create(ConfType type) {
  ConfImpl *conf = new ConfImpl(type);

  if (type == CONF_GLOBAL)
    conf->rk_conf_ = rd_kafka_conf_new();
  else
    conf->rkt_conf_ = rd_kafka_topic_conf_new();

  return conf;
}