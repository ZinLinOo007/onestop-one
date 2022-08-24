package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.one.traders.model.dto.entity.Sale;
import com.jdc.one.traders.model.dto.entity.Sale.Status;
import com.jdc.one.traders.model.dto.output.SaleSummary;
import com.jdc.one.traders.model.dto.output.SaleVO;
import com.jdc.one.traders.model.repo.SaleRepo;
import com.jdc.one.traders.model.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	private SaleRepo saleRepo;

	@Override
	public List<SaleSummary> search(Optional<String> seller, Optional<String> buyer, Optional<String> status,
			Optional<String> keyword) {
		Specification<Sale> spec = Specification.where(null);
		
		// buyer
		var buyerWhere = buyer
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Sale> where = (root, query, builder) -> builder.equal(root.get("buyer").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(buyerWhere);
		
		
		// status
		var statusWhere = status
				.filter(id -> StringUtils.hasLength(id))
				.map(Status::valueOf)
				.map(s -> {
			Specification<Sale> where = (root, query, builder) -> builder.equal(root.get("status"), s);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(statusWhere);

		// seller
		var sellerWhere = buyer
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Sale> where = (root, query, builder) -> builder.equal(root.get("product").get("seller").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(sellerWhere);
		
		// keywords
		var keywordWhere = keyword.filter(StringUtils::hasLength).map(c -> {
			Specification<Sale> where = (root, query, builder) -> builder.or(
				builder.like(builder.lower(root.get("product").get("name")),c.toLowerCase().concat("%")),
				builder.like(builder.lower(root.get("product").get("category").get("name")),c.toLowerCase().concat("%"))
			);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(keywordWhere);
		
		return saleRepo.findAll(spec).stream().map(SaleSummary::new).toList();
	}

	@Override
	public SaleVO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
