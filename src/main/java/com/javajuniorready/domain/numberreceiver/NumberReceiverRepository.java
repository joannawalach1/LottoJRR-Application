package com.javajuniorready.domain.numberreceiver;

import java.util.List;

public interface NumberReceiverRepository {
SixNumbers saveUserNumbers(SixNumbers sixNumbers);
List<SixNumbers> findAll();
SixNumbers findById(int id);
}
